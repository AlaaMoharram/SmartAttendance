class Tutorial < ApplicationRecord
  belongs_to :room
  has_many :attendance
  has_many :UserTutorial
  has_many :users, through: UserTutorial

  validates_presence_of :name, :room_id
  before_save :falsify_all_others

  private

  def falsify_all_others
    if isActive
      Tutorial.where.not(id: id).where(room_id: room_id).update_all(isActive: false)
		end
	end

end
