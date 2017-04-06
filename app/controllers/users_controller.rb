class UsersController < ApplicationController

	before_action :set_user, only: [:update, :getActiveTutorial]

	# create a new user
	def create
		@user = User.create(user_params)
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

	# get the current active tutorial for the given student
	def getActiveTutorial
		@activeTutorial = @user.tutorials.where(:isActive => true).take
		render json: @activeTutorial
	end

	# get all tutorials for a given user
	def getAllTutorials
		@allTutorials = @user.tutorials
		render json: @allTutorials
	end


	private 

	def user_params
		params.require(:user).permit(:name, :username, :pssword, :role)
	end

	def set_user
		@user = User.find(params[:id])
	end

end
