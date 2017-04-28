class Tutorial < ApplicationRecord
  belongs_to :room
  has_many :attendance
  has_many :UserTutorial
  has_many :users, through: UserTutorial

  validates_presence_of :name, :room_id

end
