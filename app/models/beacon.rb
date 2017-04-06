class Beacon < ApplicationRecord
  belongs_to :room

  validates_presence_of :uuid, :room_id
end
