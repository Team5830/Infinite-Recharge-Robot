
package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ExtendHook extends InstantCommand {
  Climber m_climber;
  public ExtendHook(Climber climber_in) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = climber_in;
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(!m_climber.isclimberon){
      m_climber.ClimberOn();
    } else{
      m_climber.ClimberOff();
    }
    
  }
  


}


