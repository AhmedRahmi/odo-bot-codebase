package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous
public class TestAuto extends LinearOpMode {

    MecanumDrive drive;
    Action trajectory;

    @Override
    public void runOpMode () {
        ElapsedTime timer = new ElapsedTime();

        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(0)));

        trajectory = drive.actionBuilder(drive.pose)
                .lineToXSplineHeading(40, Math.toRadians(0))
                .turn(Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .lineToY(40)
                .turn(Math.toRadians(90))
                .setTangent(Math.toRadians(0))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(90))
                .build();

        while(!isStarted() && !opModeIsActive()) {}

        Actions.runBlocking(trajectory);

        Actions.runBlocking(trajectory);
    }

}
