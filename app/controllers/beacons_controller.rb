class BeaconsController < ApplicationController

	skip_before_action :verify_authenticity_token

	# get all beacons
	def index
		@allBeacons = Beacon.all
		if @allBeacons
			render json: @allBeacons
		else
			render json: {}
		end
	end
	
	# create a new beacon
	def create
	  	@beacon = Beacon.new(beacon_params)
		if @beacon.save
			render json: @beacon, status: :created
		else
			 render json: @beacon.errors, status: :unprocessable_entity
		end 
  	end


  	private
  	
 	def beacon_params
		params.permit(:uuid, :room_id)
	end

end
