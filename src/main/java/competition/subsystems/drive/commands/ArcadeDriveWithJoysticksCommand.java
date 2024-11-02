package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.operator_interface.OperatorInterface;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

public class ArcadeDriveWithJoysticksCommand extends BaseCommand {

    OperatorInterface operatorInterface;
    DriveSubsystem drive;

    @Inject
    public ArcadeDriveWithJoysticksCommand(DriveSubsystem driveSubsystem, OperatorInterface oi) {
        this.operatorInterface = oi;
        this.drive = driveSubsystem;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double leftValue = operatorInterface.gamepad.getLeftVector().y;
        double rightValue = leftValue;

        //if

        drive.tankDrive(leftValue, rightValue);
    }

}
