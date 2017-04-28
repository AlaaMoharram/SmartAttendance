class AttendancesController < ApplicationController

	skip_before_action :verify_authenticity_token
	before_action :set_attendance_by_id, only: [:update]

	# get all attendances
	def index
		@allAttendances = Attendance.all
		if @allAttendances
			render json: @allAttendances
		else
			render json: {}
		end
	end
	
	# create a new attendance record
	def create
		@attendance = Attendance.new(attendance_params)
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
	end



	private 

	def attendance_params
		params.permit(:user_id, :tutorial_id, :attended, :tut_date, :tut_start_time, :tut_end_time)
	end

	def set_attendance_by_id
		@attendance = Attendance.find(params[:id])
	end

end
