class Beacon < ApplicationRecord
  belongs_to :room

  validates_presence_of :beacon_name, :room_id
end
