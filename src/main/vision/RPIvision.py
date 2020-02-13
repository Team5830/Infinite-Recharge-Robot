#!/usr/bin/env python3
import cv2
import numpy as np
import imutils
from collections import deque
from networktables import NetworkTablesInstance
from networktables import NetworkTables
from cscore import CameraServer, VideoSource, UsbCamera, MjpegServer
import ntcore
import json,sys
from GripHighTarget import GripHighTarget

def parseError(str):
    """Report parse error."""
    print("config error in '" + configFile + "': " + str, file=sys.stderr)

def readCameraConfig(config):
    """Read single camera configuration."""
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read camera name")
        return False

    # path
    try:
        cam.path = config["path"]
    except KeyError:
        parseError("camera '{}': could not read path".format(cam.name))
        return False

    # stream properties
    cam.streamConfig = config.get("stream")

    cam.config = config

    cameraConfigs.append(cam)
    return True

def readSwitchedCameraConfig(config):
    """Read single switched camera configuration."""
    cam = CameraConfig()

    # name
    try:
        cam.name = config["name"]
    except KeyError:
        parseError("could not read switched camera name")
        return False

    # path
    try:
        cam.key = config["key"]
    except KeyError:
        parseError("switched camera '{}': could not read key".format(cam.name))
        return False

    switchedCameraConfigs.append(cam)
    return True

def readConfig():
    """Read configuration file."""
    global team
    global server

    # parse file
    try:
        with open(configFile, "rt", encoding="utf-8") as f:
            j = json.load(f)
    except OSError as err:
        print("could not open '{}': {}".format(configFile, err), file=sys.stderr)
        return False

    # top level must be an object
    if not isinstance(j, dict):
        parseError("must be JSON object")
        return False

    # team number
    try:
        team = j["team"]
    except KeyError:
        parseError("could not read team number")
        return False

    # ntmode (optional)
    if "ntmode" in j:
        str = j["ntmode"]
        if str.lower() == "client":
            server = False
        elif str.lower() == "server":
            server = True
        else:
            parseError("could not understand ntmode value '{}'".format(str))

    # cameras
    try:
        cameras = j["cameras"]
    except KeyError:
        parseError("could not read cameras")
        return False
    for camera in cameras:
        if not readCameraConfig(camera):
            return False

    # switched cameras
    if "switched cameras" in j:
        for camera in j["switched cameras"]:
            if not readSwitchedCameraConfig(camera):
                return False
    return True

def startCamera(config):
    """Start running the camera."""
    print("Starting camera '{}' on {}".format(config.name, config.path))
    inst = CameraServer.getInstance()
    camera = UsbCamera(config.name, config.path)
    server = inst.startAutomaticCapture(camera=camera, return_server=True)
    camera.setConfigJson(json.dumps(config.config))
    camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen)

    if config.streamConfig is not None:
        server.setConfigJson(json.dumps(config.streamConfig))

    return camera,inst

def startSwitchedCamera(config):
    """Start running the switched camera."""
    print("Starting switched camera '{}' on {}".format(config.name, config.key))
    server = CameraServer.getInstance().addSwitchedCamera(config.name)

    def listener(fromobj, key, value, isNew):
        if isinstance(value, float):
            i = int(value)
            if i >= 0 and i < len(cameras):
              server.setSource(cameras[i])
        elif isinstance(value, str):
            for i in range(len(cameraConfigs)):
                if value == cameraConfigs[i].name:
                    server.setSource(cameras[i])
                    break

    NetworkTablesInstance.getDefault().getEntry(config.key).addListener(
        listener,
        ntcore.constants.NT_NOTIFY_IMMEDIATE |
        ntcore.constants.NT_NOTIFY_NEW |
        ntcore.constants.NT_NOTIFY_UPDATE)

    return server

configFile = "/boot/frc.json"
class CameraConfig: pass
team = None # Get from config
server = False
cameraConfigs = []
switchedCameraConfigs = []
cameras = []
roboRioIP = '10.58.30.2'
if __name__ == "__main__":
    if len(sys.argv) >= 2:
        configFile = sys.argv[1]
    # read configuration
    if not readConfig():
        sys.exit(1)
    # start NetworkTables
    ntinst = NetworkTablesInstance.getDefault()
    if server:
        print("Setting up NetworkTables server")
        ntinst.startServer()
    else:
        print("Setting up NetworkTables client for team {}".format(team))
        ntinst.startClientTeam(team)
    first_config = None
    # start cameras
    for config in cameraConfigs:
        camera,cs = startCamera(config)
        cameras.append(camera)
        if first_config is None:
            first_config = config
    # start switched cameras
    for config in switchedCameraConfigs:
        startSwitchedCamera(config)
    cvSink = cs.getVideo()
    imgWidth = float(first_config.config['width'])
    imgHeight = float(first_config.config['height'])
    img = np.zeros(shape=(imgWidth,imgHeight, 3), dtype=np.uint8)
    NetworkTables.initialize(server=roboRioIP)
    sd = NetworkTables.getTable("SmartDashboard")
    # Switch cameras based on Dashboard
    vCam = 0
    outputStream = cs.putVideo("Camera %d"%vCam, imgWidth,imgHeight)
    pipline_init = -1
    while True:
        # Vision Target
        vTarget = 0 # Get from Dashboard
        # Initialized first time or if target changes
        if vTarget != pipeline_init:
            if vTarget == 0:
                gpipeline = GripHighTarget()
                cmpMask = cv2.imread('mask_1.jpg',0)
                gpipeline.process(cmpMask)
                cntMask = gpipeline.filter_contours_output
            elif vTarget == 1:
                gpipeline = None # Define GripBallTarget()
        time, img = cvSink.grabFrame(img)
        if time == 0:
            outputStream.notifyError(cvSink.getError());
            # skip the rest of the current iteration
            continue
        gpipeline.process(img)
        cnts = gpipeline.filter_contours_output
        c = None
        if len(cnts) > 0:
            if vTarget == 0:
                # Select Contours
                score = []
                for cnt in cnts:
                    score.append(cv2.matchShapes(cntMask,cnt,1,0.0))
                if len(score) > 0 and min(score) < 1:
                    c = cnts[score.index(min(score))]
                if c is not None:
                    # Get parameters to return
                    leftmost = tuple(c[c[:,:,0].argmin()][0])
                    rightmost = tuple(c[c[:,:,0].argmax()][0])
                    x = (leftmost[0] + rightmost[0])/2 
                    y = (leftmost[1] + rightmost[1])/2
                    length = rightmost[0] -leftmost[0]
                    sd.putNumber("HT xPosition",int(x))
                    sd.putNumber("HT yPosition",int(y))
                    sd.putNumber("HT length",int(length))
                    sd.putFloat("HT score",min(score))
                    #  Mark in image
                    cv2.circle(img, (int(x), int(y)), int(10),(0, 255, 255), 2)
            if vTarget == 1:
                # Select Contours
                c = max(cnts, key=cv2.contourArea)
                ((x, y), radius) = cv2.minEnclosingCircle(c)
                M = cv2.moments(c)
                center = (int(M["m10"] / M["m00"]), int(M["m01"] / M["m00"]))
                if radius > 10:
                    cv2.circle(img, (int(x), int(y)), int(radius),
                        (0, 255, 255), 2)
                    cv2.circle(img, center, 5, (0, 0, 255), -1)
                    sd.putNumber("Ball xPosition",int(x))
                    sd.putNumber("Ball yPosition",int(y))
        outputStream.putFrame(img)
