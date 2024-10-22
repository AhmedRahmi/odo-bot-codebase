package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
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

        drive = new MecanumDrive(hardwareMap, new Pose2d(6, 108, Math.toRadians(0)));


        fullAuto = drive.actionBuilder(drive.pose)
                .splineToConstantHeading(new Vector2d(30, 84), 0)
                .waitSeconds(3)
                .strafeTo(new Vector2d(30, 120))
                .turnTo(Math.PI)
                .splineToSplineHeading(new Pose2d(12, 132, Math.PI * 0.75), Math.PI)
                .build();

        while(!isStarted() && !opModeIsActive()) {}

        Actions.runBlocking(fullAuto);

        Actions.runBlocking(fullAuto);
    }





}
