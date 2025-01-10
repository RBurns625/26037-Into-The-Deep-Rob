package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

@SuppressWarnings("unused")

public class TuningTest {
    private static final double TILE_WIDTH   = 23.5;
    private static final double TILE_HEIGHT  = 23.5;
    private static final double ROBOT_HEIGHT = 18;

    private final MecanumDrive drive;

    private static final double ROBOT_STARTING_POSITION_Y = 3 * TILE_HEIGHT - ROBOT_HEIGHT / 2;
    private static final double ROBOT_STARTING_POSITION_X = TILE_WIDTH * 1.5 - 3;

    public TuningTest(HardwareMap hardwareMap) {
        Pose2d beginningPose = new Pose2d(ROBOT_STARTING_POSITION_X, ROBOT_STARTING_POSITION_Y, Math.toRadians(270));
        drive = new MecanumDrive(hardwareMap,beginningPose);
    }

    public void runAutonomous() {
        double lwrRightCornerX  = 1.0;
        double lwrRightCornerY  = 1.0;

        double upperRightCornerX = 1.0;
        double upperRightCornerY = 1.0;

        double upperLeftCornerX  = 1.0;
        double upperRightCornerY = 1.0;

        // Strafe to Lower Right Corner and spin 360deg

        drive.setExtraCorrectionTime(1);
        Actions.runBlocking(
                drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(lwrRightCornerX, lwrRightCornerY, Math.toRadians(315)),Math.toRadians(315))
                .build());

        // Strafe to Upper Right Corner and spin 360deg

        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(315))
                .splineToLinearHeading(new Pose2d(lwrRightCornerX, lwrRightCornerY,Math.toRadians(315)),Math.toRadians(45))
                .build());

        // Strafe to Upper Left Corner and spin 360deg

        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(135))
                .splineToLinearHeading(new Pose2d(lwrRightCornerX, lwrRightCornerY,Math.toRadians(0)),Math.toRadians(45))
                .build());

        // Strafe to Y0 and end at facing submersible
        
        Actions.runBlocking(drive.actionBuilder(drive.pose)
                .setTangent(Math.toRadians(180))
                .splineToLinearHeading(new Pose2d(lwrRightCornerX, lwrRightCornerY,Math.toRadians(0)),Math.toRadians(45))
                .build());
    }
}
