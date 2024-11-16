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
        oldPosition = pose.getPosition();
        double positionDif = pose.getPosition() - oldPosition;
        double range = this.goal - pose.getPosition();
        double power = range * .8 - positionDif*2;;
        drive.tankDrive(-power, -power);

    }

    @Override
    public boolean isFinished() {
        // Modify this to return true once you have met your goal,
        double range = this.goal - pose.getPosition();
        double positionDif = pose.getPosition() - oldPosition;

        if (abs(positionDif) < .01 && range < .01 && range > 0) {
            return true;
        }
        return false;
    }
}
