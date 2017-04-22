class RoomsController < ApplicationController

	before_action :set_room_by_id, only: [:getRoomID, :getBeacons]

	# create a new room
	def create
		@room = Room.create(room_params)
		if @room.save
			render json: @room, status: :created
		else
			 render json: @room.errors, status: :unprocessable_entity
		end 
	end

	# get all beacons for a certain room
	def getBeacons
		@room = Room.where(:name => params[:name]).take
		@beacons = @room.beacons
		render json: @beacons
	end


	private

	def room_params
		params.require(:room).permit(:name)
	end

	def set_room_by_id
		@room = Room.find(params[:id])
	end

end
