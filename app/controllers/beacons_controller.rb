class BeaconsController < ApplicationController
	
	def create
	  	@beacon = Beacon.create(beacon_params)
		if @beacon.save
			render json: @beacon, status: :created
		else
			 render json: @beacon.errors, status: :unprocessable_entity
		end 
  	end

  	private
  	
 	def beacon_params
		params.require(:beacon).permit(:uuid, :room_id)
	end

end
