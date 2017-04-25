class TutorialsController < ApplicationController

	skip_before_action :verify_authenticity_token
	before_action :set_tutorial_by_name, only: [:update, :findRoom, :getAllStudents, :getAllAttendances, :show, :generateAttendances]
	after_action :generateAttendances, only: [:update], if: -> {params[:isActive]==true}

	def index
		@allTutorials = Tutorial.all
		if @allTutorials
			render json: @allTutorials
		else
			render json: {}
		end

	end

	# create a new tutorial 
	def create
		@tut = Tutorial.new(tut_params)
		if @tut.save
			render json: @tut, status: :created
		else
			 render json: @tut.errors, status: :unprocessable_entity
		end 
	end

	# get a certain tutorial
	def show
		render json: @tutorial
	end

	#update a specific tutorial
	def update
		if @tutorial.update(tut_params)
			head :no_content
		else
			render json: @tutorial.errors, status: :unprocessable_entity
		end
	end

	# find the room for the given tutorial
	def findRoom
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
		@allAttendances = @tutorial.attendance.all
		render json: @allAttendances
	end

	# once a specific tutorial is activated, generate false attendance records for associated students
	def generateAttendances
		@allStudents = @tutorial.users.where(:role => 0)
		@tid = @tutorial.id
		@currDate = Time.now.to_date.strftime("%d-%m-%Y")
		@currTime = Time.now.to_time.strftime("%H:%M:%S")
		@allStudents.each do |s|
			@sid = s.id
			@attendance = Attendance.new(:user_id => @sid, :tutorial_id => @tid, :tut_date => @currDate, :tut_time => @currTime)
			@attendance.save
		end
	end


	private 

	def tut_params
		params.permit(:name, :isActive, :room_id)
	end

	def set_tutorial_by_name
		@tutorial = Tutorial.where(:name => params[:name]).first
	end

end
