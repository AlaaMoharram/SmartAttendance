class TutorialsController < ApplicationController

	before_action :set_tutorial_by_name, only: [:findRoom, :getAllStudents, :getAllAttendances]
	before_action :set_tutorial_by_id, only: [:update]

	# create a new tutorial 
	def create
		@tut = Tutorial.create(tut_params)
		if @tut.save
			render json: @tut, status: :created
		else
			 render json: @tut.errors, status: :unprocessable_entity
		end 
	end

	# update an existing tutorial
	def update
		if @tutorial.update(tut_params)
			head :no_content
		else
			render json: @tutorial.errors, status: :unprocessable_entity
	end

	# find the room for the given tutorial
	def findRooms
		@room = @tutorial.room
		render json: @room
	end

	# find all students associated with the given tutorial
	def getAllStudents
		@allStudents = @tutorial.users.where(:role => 0)
		render json: @allStudents
	end

	# get all attendance records for a certain tutorial
	def getAllAttendances
		@allAttendances = @tutorial.attendances.all
		render json: @allAttendances
	end


	private 

	def tut_params
		params.require(:tutorial).permit(:name, :isActive, :room_id)
	end

	def set_tutorial_by_name
		@tutorial = Tutorial.where(:name => params[:name])
	end

	def set_tutorial_by_id
		@tutorial = Tutorial.find(params[:id])
	end

end
