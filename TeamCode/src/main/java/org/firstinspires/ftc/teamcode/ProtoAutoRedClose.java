package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class ProtoAutoRedClose extends LinearOpMode {

    MecanumDrive drive;
    Action fullAuto;

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();

        drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -63, Math.toRadians(90)));


        Action depositSpecimen = drive.actionBuilder(drive.pose)
                .splineToConstantHeading(new Vector2d( -12, -36), Math.PI * 0.5)
                .waitSeconds(3)
                .turnTo(Math.PI)
                .splineToConstantHeading(new Vector2d(-36, -24), Math.PI * 0.5)
                .build();

        Action park = drive.actionBuilder(new Pose2d(-60, -25.5, Math.PI))
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(-28, -12, 0), 0)
                .build();

        fullAuto = new SequentialAction(
                depositSpecimen,
                cycleBlock (new Vector2d(-36, -25.5)),
                cycleBlock (new Vector2d(-48, -25.5)),
                park
        );

        while(!isStarted() && !opModeIsActive()) {}

        Actions.runBlocking(fullAuto);

    }


    // TODO: Spitballin!
    // alr we gotta get this to cycle pointing towards the bottom of the field
    // end positions should be (-63, -63)
    // cycle to like (-36, -25.75), add 12 to the size ever time

    Action cycleBlock (Vector2d origin) {
        return drive.actionBuilder(new Pose2d(origin, Math.PI))
                .waitSeconds(2)
                .setTangent(Math.toRadians(-90))
                .splineToConstantHeading(new Vector2d(-60, -60), Math.toRadians(180))
                .waitSeconds(0.5)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(origin.x - 12, origin.y), Math.toRadians(90))
                .build();
    }


}
