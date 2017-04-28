class SessionsController < ApplicationController

	skip_before_action :verify_authenticity_token

	# login
	def create
		user = User.where(:username => params[:session][:username].downcase, :password => params[:session][:password]).first
		if user
			session[:user_id] = user.id
	  		render json: user, status: 201
	  	else
	  		render json: {}, status: 401
	  	end
	end

	# logout
	def destroy
    	session[:user_id] = nil
    	head :no_content
  	end

end
