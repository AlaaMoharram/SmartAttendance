class AttendancesController < ApplicationController

	before_action :set_attendance, only: [:update]
	
	# create a new attendance record
	def create
		@attendance = Attendance.create(attendance_params)
		if @attendance.save
			render json: @attendance, status: :created
		else
			 render json: @attendance.errors, status: :unprocessable_entity
		end 
	end

	# update an existing attendance record
	def update
		if @attendance.update(attendance_params)
			head :no_content
		else
			render json: @attendance.errors, status: :unprocessable_entity
	end

	# get all attendance records for a certain student
	def getAllForStudent
		@allAttendances = Attendance.where(:user_id => params[:user_id]).all
		render json: @allAttendances
	end

	# get all attendance records for a certain tutorial
	def getAllForTutorial
		@allAttendances = Attendance.where(:tutorial_id => params[:tutorial_id], :tut_date => params[:tut_date], :tut_time => params[:tut_time]).all
		render json: @allAttendances
	end

	private 

	def attendance_params
		params.require(:attendance).permit(:user_id, :tutorial_id, :attended, :tut_date, :tut_time)
	end

	def set_attendance
		@attendance = Attendance.find(params[:id])
	end

end
