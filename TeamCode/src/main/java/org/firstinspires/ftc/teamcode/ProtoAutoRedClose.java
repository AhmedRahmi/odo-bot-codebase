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

        drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -66, Math.toRadians(90)));


        Action depositSpecimen = drive.actionBuilder(drive.pose)
                .splineToConstantHeading(new Vector2d( -12, -36), Math.PI)
                .waitSeconds(3)
                .strafeTo(new Vector2d(-48, -36))
                .build();

        fullAuto = new SequentialAction(
                depositSpecimen,
                cycleBlock (new Vector2d(-48, -36)),
                cycleBlock (new Vector2d(-60, -36))
        );

        while(!isStarted() && !opModeIsActive()) {}

        Actions.runBlocking(fullAuto);

    }




    Action cycleBlock (Vector2d origin) {
        return drive.actionBuilder(new Pose2d(origin, Math.PI * 0.5))
                .waitSeconds(5)
                .splineToConstantHeading(new Vector2d(-60,-60), -Math.PI * 0.5)
                .waitSeconds(5)
                .splineToConstantHeading(new Vector2d(origin.x - 12, origin.y), -Math.PI * 0.5)
                .build();
    }


}
