class RoomsController < ApplicationController

	skip_before_action :verify_authenticity_token


	# get all rooms
	def index
		@allRooms = Room.all
		if @allRooms
			render json: @allRooms
		else
			render json: {}
		end
	end

	# get a specific room
	def show
		@room = Room.where(:name => params[:name]).first
		if @room
			render json: @room
		else
			render json: {}
		end
	end

	# create a new room
	def create
		@room = Room.new(room_params)
		if @room.save
			render json: @room, status: :created
		else
			 render json: @room.errors, status: :unprocessable_entity
		end 
	end

	# get all beacons for a certain room
	def getBeacons
		@room = Room.where(:id=> params[:id]).take
		@beacons = @room.beacon.all
		render json: @beacons
	end


	private

	def room_params
		params.permit(:name, :id)
	end

end
