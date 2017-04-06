class RoomsController < ApplicationController

	before_action :set_room, only: [:getRoomID, :getBeacons]

	# create a new room
	def create
		@room = Room.create(room_params)
		if @room.save
			render json: @room, status: :created
		else
			 render json: @room.errors, status: :unprocessable_entity
		end 
	end

	# find a room's ID given its name
	def getRoomID
		@room = Room.where(:name => params[:name]).take
		render json: @room
	end

	# get all beacons for a certain room
	def getBeacons
		@room = Room.where(:name => params[:name]).all
		@beacons = @room.beacon
		render json: @beacons
	end


	private

	def room_params
		params.require(:room).permit(:name)
	end

	def set_room
		@room = Room.find(params[:id])
	end

end
