package competition.subsystems.drive.commands;

import javax.inject.Inject;

import xbot.common.command.BaseCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.pose.PoseSubsystem;

import static java.lang.Math.abs;

public class DriveToPositionCommand extends BaseCommand {

    DriveSubsystem drive;
    PoseSubsystem pose;
    double goal;
    double oldPosition;

    @Inject
    public DriveToPositionCommand(DriveSubsystem driveSubsystem, PoseSubsystem pose) {
        this.drive = driveSubsystem;
        this.pose = pose;
    }

    public void setTargetPosition(double position) {
        // This method will be called by the test, and will give you a goal distance.
        // You'll need to remember this target position and use it in your calculations.
        this.goal = position;

    }

    @Override
    public void initialize() {
        // If you have some one-time setup, do it here.
    }

    @Override
    public void execute() {
        // Here you'll need to figure out a technique that:
        // - Gets the robot to move to the target position
        // - Hint: use pose.getPosition() to find out where you are
        // - Gets the robot stop (or at least be moving really really slowly) at the
        // target position

        // How you do this is up to you. If you get stuck, ask a mentor or student for
        // some hints!
        pose.getPosition();
        double positionDif = pose.getPosition() - oldPosition;
        double range = this.goal - pose.getPosition();
        double power = range * 5 - positionDif*50;;
        drive.tankDrive(-power, -power);
        oldPosition = pose.getPosition();

        System.out.println("Range: " + range);
        System.out.println("Position Difference: " + positionDif);
        System.out.println("Current Position: " + pose.getPosition());
        System.out.println("Old Position:" + oldPosition);
    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        double range = this.goal - pose.getPosition();
        double positionDif = pose.getPosition() - oldPosition;

        if (abs(positionDif) < .001 && range < .001 && range > 0) {
            return true;
        }
        return false;
    }
}
