package org.firstinspires.ftc.teamcode;

//import com.google.blocks.ftcrobotcontroller.runtime.CRServoAccess;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class TesterTeleop extends LinearOpMode {

    private com.qualcomm.robotcore.hardware.HardwareMap HardwareMap;
    BaseRobot robot;

    int leftSliderPos = 0;
    int rightSliderPos = 0;
    int slidesPos = 0;

    int hangArmPos = 0;

    int pivotMotorPos = 0;

    double gimbalServoPos = 0;
    double gimbalServoChange = .05;//change

    double gimbalServoReset = 0; //change

    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive d = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new BaseRobot(hardwareMap);

        //motor encoder setup
        robot.hangArm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.hangArm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        hangArmPos = robot.hangArm.getCurrentPosition();

        robot.leftSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightSlider.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightSlider.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftSliderPos = robot.leftSlider.getCurrentPosition();
        rightSliderPos = robot.rightSlider.getCurrentPosition();
        slidesPos = robot.rightSlider.getCurrentPosition();

        robot.pivotMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.pivotMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pivotMotorPos = robot.pivotMotor.getCurrentPosition();
        //end

        gimbalServoPos = robot.grasperGimbal.getPosition();

        waitForStart();

        while(!isStopRequested() && opModeIsActive()){


        robot.drive.setDrivePowers(new PoseVelocity2d(new Vector2d(-gamepad1.left_stick_y, -gamepad1.left_stick_x), gamepad1.right_stick_x));

            //worm gear box
            robot.pivotMotor.setPower(-gamepad2.left_stick_y);
            //end worm gear box

            //hang arm
            int hangArmMotorDelta = (int) ((gamepad2.right_trigger - gamepad2.left_trigger) * 10);

            if(Math.abs(hangArmMotorDelta) > .1){
                hangArmPos += hangArmMotorDelta;
            }
            robot.hangArm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.hangArm.setTargetPosition(hangArmPos);
            robot.hangArm.setPower(1);

            //end hang arm
            //_____________________________________________________________________________________


            //gimbal servo
            if (gamepad2.left_bumper){
                gimbalServoPos -= gimbalServoChange;
            }
            if(gamepad2.right_bumper){
                gimbalServoPos += gimbalServoChange;
            }
            if (gamepad2.a){
                gimbalServoPos = gimbalServoReset;
            }

            //end gimbal servo


            //slides
            int  slidesMotorDelta = (int) (-gamepad2.right_stick_y * 10);

                if(Math.abs(slidesMotorDelta) > .1) {
                //leftSliderPos += slidesMotorDelta;
                //rightSliderPos += slidesMotorDelta;
                slidesPos += slidesMotorDelta;
            }

            if (gamepad1.dpad_down){
                //robot.slidesDown();//sets slides pos to 0
                //robot.(0);
                slidesPos = 0;
            }

            if(gamepad2.a){
                robot.axleRotation.setPosition(.6);
            }
            if(gamepad2.b){
                robot.axleRotation.setPosition(0);
            }
            if(gamepad2.y){
                robot.axleRotation.setPosition(.4);
            }
            if(gamepad2.x){
                robot.axleRotation.setPosition(.8);
            }

            //robot.leftSlider.setTargetPosition(leftSliderPos);
            //robot.rightSlider.setTargetPosition(rightSliderPos);
            robot.leftSlider.setTargetPosition(slidesPos);
            robot.rightSlider.setTargetPosition(slidesPos);


            /*if(rightSliderPos < 0){
                rightSliderPos = 0;
            }
            if(leftSliderPos < 0){
                leftSliderPos = 0;
            }
            if(leftSliderPos > 3000) {//change number
                leftSliderPos = 3000;
            }
            if(rightSliderPos > 3000) {//change number
                rightSliderPos = 3000;
            }*/
            if (slidesPos < 0){
                slidesPos = 0;
            }
            if (slidesPos > 3000){
                slidesPos = 3000;//change number
            }


            //end slides
            //_____________________________________________________________________________________


            /*if (gamepad1.dpad_left){
                robot.reachToSub();
            }
            if (gamepad1.dpad_right){
                //robot.sliderReset();
            }
            if (gamepad1.dpad_up){
                robot.basketScoring();
            }
            if (gamepad1.y){
                robot.specimenScoring();
            }
            */

            //telemetry
            //_____________________________________________________________________________________
            telemetry.addData("left slides position: ", leftSliderPos);
            telemetry.addData("hang arm position: ", hangArmPos);
            telemetry.addData("right slides position: ", rightSliderPos);
            telemetry.addData("pivot motor position: ", pivotMotorPos);
            telemetry.addData("Slides motor delta: ", slidesMotorDelta);

            telemetry.update();
            //end telemetry
            //_____________________________________________________________________________________
        }
    }
}