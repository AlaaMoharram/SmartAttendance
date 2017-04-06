class Room < ApplicationRecord
	has_many :tutorial
	has_many :beacon

	validates_presence_of :name
end

