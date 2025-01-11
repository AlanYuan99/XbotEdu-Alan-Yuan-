package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

import static java.lang.Math.abs;
import static java.lang.Math.toDegrees;

public class TurnLeft90DegreesCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double goal;
    double oldPosition;
    double currentPosition;

    @Inject
    public TurnLeft90DegreesCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
        this.goal = 90;
    }

    @Override
    public void initialize() {



    }

    @Override
    public void execute() {

        currentPosition = pose.getCurrentHeading().getDegrees();
        double positionDif = currentPosition - oldPosition;
        double range = goal - currentPosition;
        double power = range * .9 - positionDif*.9;;
        drive.tankDrive(power, -power);
        oldPosition = pose.getCurrentHeading().getDegrees();

        System.out.println("Power: " + power);
        System.out.println("Range: " + range);
        System.out.println("Position Difference: " + positionDif);
        System.out.println("Current Position: " + currentPosition);
        System.out.println("Old Position:" + oldPosition);

    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        double range = this.goal - pose.getCurrentHeading().getDegrees();
        double positionDif = currentPosition - oldPosition;
        if (range == 0 && abs(positionDif) == 0 && currentPosition == 90) {
            return true;
        }
        return false;
    }

}
