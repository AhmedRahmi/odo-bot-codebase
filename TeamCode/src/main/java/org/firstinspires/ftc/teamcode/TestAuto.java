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

    @Override
    public void runOpMode () {
        ElapsedTime timer = new ElapsedTime();

        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, Math.toRadians(0)));



        while(!isStarted() && !opModeIsActive()) {}

        while(opModeIsActive() && !isStopRequested()) {
            Action trajectory = drive.actionBuilder(drive.pose)
                    .lineToXSplineHeading(10, Math.toRadians(0))
                    .turn(Math.toRadians(90))
                    .setTangent(Math.toRadians(90))
                    .lineToY(10)
                    .turn(Math.toRadians(90))
                    .setTangent(Math.toRadians(0))
                    .lineToX(0)
                    .turn(Math.toRadians(90))
                    .setTangent(Math.toRadians(90))
                    .lineToY(0)
                    .turn(Math.toRadians(90))
                    .build();

            Actions.runBlocking(trajectory);
        }

    }

}
