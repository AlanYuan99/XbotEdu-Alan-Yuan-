package competition.subsystems.drive.commands;

import javax.inject.Inject;

import competition.subsystems.pose.PoseSubsystem;
import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;

import static java.lang.Math.abs;

public class DriveToOrientationCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;

    double goal;
    double currentPosition;
    double oldPosition;
    double range;
    double positionDif;
    double power;

    @Inject
    public DriveToOrientationCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }

    public void setTargetHeading(double heading) {
        // This method will be called by the test, and will give you a goal heading.
        // You'll need to remember this target position and use it in your calculations.
        this.goal = heading;
    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to turn to the target orientation
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        currentPosition = pose.getCurrentHeading().getDegrees();
        positionDif = currentPosition - oldPosition;
        range = goal - currentPosition;
        power = range * .9 - positionDif * 1.1;
        drive.tankDrive(power, -power);
        oldPosition = pose.getCurrentHeading().getDegrees();

        System.out.println("Power: " + power);
        System.out.println("Range: " + range);
        System.out.println("Position Difference: " + positionDif);
        System.out.println("Current Position: " + currentPosition);
        System.out.println("Old Position:" + oldPosition);

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!
    }

    @Override
    public boolean isFinished() {
        double range = this.goal - pose.getCurrentHeading().getDegrees();
        double positionDif = currentPosition - oldPosition;

        if (range == 0 && abs(positionDif) == 0 & currentPosition == goal) {
            return true;
        }
        // Modify this to return true once you have met your goal,
        // and you're moving fairly slowly (ideally stopped)
        return false;
    }
}
