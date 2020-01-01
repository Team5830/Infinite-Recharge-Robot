# Infinite-Recharge-Robot
Team 5830's robot code for FIRST Infinite Recharge (2020)

---

## Developer Policy

#### All Team 5830 developers MUST follow this policy. Commits and pull requests not adhering to this policy will be automatically rejected.

##### Issues
Whenever a change is required, an issue must be created.
- All issues must be added to appropriate projects (e.g. Drivetrain, Manipulator, etc). For example, ALL issues created before Stop Build must be added to Stop Build project.
- All issues must have a tag associated with them. The two common tags to be used are `enhancement` and `bug`. If something needs to be added, it is an `enhancement`. If something isn't working or the code is crashiing, it is a `bug`.
- If you will be working on this issue, self-assign it, or assign it to someone else after talking to them. Otherwise, leave it unassigned.
- Give a detailed description of the issue or additions required. Any 5830 developer must be able to complete the task using the information provided.

##### Branches
Whenever a developer decides to solve an issue, they must create a new branch with a relevant name. Upon completion, the code must be successfully simulated. Once completed, a pull request must be compared with `development` branch. An admin will merge it and delete the branch.

##### `stable` branch
Weekly, after every function of all robot code is successfully simulated or tested on robot (if available), an admin will merge `development` with `stable`. No incomplete, broken, or buggy code will be merged with stable. Drivers and other robot operators should rely on this branch to always contain working code to test or drive with.
