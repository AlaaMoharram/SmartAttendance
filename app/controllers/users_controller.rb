class UsersController < ApplicationController

	skip_before_action :verify_authenticity_token
	before_action :set_user_by_id, only: [:update]
	before_action :set_user_by_username, only: [:getActiveTutorial, :getAllTutorials, :getTutAttendances]

	def index
		@allUsers = User.all
		if @allUsers
			render json: @allUsers
		else
			render json: {}
		end

	end

	# create a new user
	def create
		@user = User.new(user_params)
		if @user.save
			render json: @user, status: :created
		else
			 render json: @user.errors, status: :unprocessable_entity
		end 
	end

	# update an existing user record
	def update
		if @user.update(user_params)
			head :no_content
		else
			render json: @user.errors, status: :unprocessable_entity
		end
	end

	# get the current active tutorial for the given student
	def getActiveTutorial
		@activeTutorial = @user.tutorials.where(:isActive => true).take
		if @activeTutorial
			render json: @activeTutorial
		else
			render json: {}
		end
	end

	# get all tutorials for a given user
	def getAllTutorials
		@allTutorials = @user.tutorials
		if @allTutorials
			render json: @allTutorials
		else
			render json: {}
		end
	end

	# get all attendances for a certain student for a certain tutorial
	def getTutAttendances
		@tutorial = @user.tutorials.where(:name => params[:name]) 
		@attendances = @tutorial.attendances.all
	end


	private 

	def user_params
		params.permit(:name, :username, :password, :role)
	end

	def set_user_by_id
		@user = User.find(params[:id])
	end

	def set_user_by_username
		@user = User.where(:username => params[:username])
	end

end
