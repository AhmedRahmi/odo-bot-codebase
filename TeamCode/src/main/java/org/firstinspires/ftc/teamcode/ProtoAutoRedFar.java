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
public class ProtoAutoRedFar extends LinearOpMode {

    MecanumDrive drive;
    Action fullAuto;

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime timer = new ElapsedTime();

        drive = new MecanumDrive(hardwareMap, new Pose2d(12, -63, Math.toRadians(90)));


        fullAuto = drive.actionBuilder(drive.pose)
                .splineToConstantHeading(new Vector2d( 6, -36), Math.PI * 0.5)
                .waitSeconds(3)
                .setTangent(0)
                .splineToConstantHeading(new Vector2d(36, -24), Math.PI * 0.5)
                .setTangent(Math.PI * 0.5)
                .splineToSplineHeading(new Pose2d(28, -12, Math.PI), Math.PI)
                .build();


        while(!isStarted() && !opModeIsActive()) {}

        Actions.runBlocking(fullAuto);

    }


    // TODO: Spitballin!
    // alr we gotta get this to cycle pointing towards the bottom of the field
    // end positions should be (-63, -63)
    // cycle to like (-36, -25.75), add 12 to the size ever time




}

