class AttendancesController < ApplicationController

	before_action :set_attendance_by_id, only: [:update]
	before_action :set_user, only: [:getAllForStudent]
	before_action :set_tutorial, only: [:getAllForTutorial]
	
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



	private 

	def attendance_params
		params.require(:attendance).permit(:user_id, :tutorial_id, :attended, :tut_date, :tut_time)
	end

	def set_attendance_by_id
		@attendance = Attendance.find(params[:id])
	end

end
