class UserTutorial < ApplicationRecord
  belongs_to :user
  belongs_to :tutorial

  validates :user_id, presence: true
  validates :tutorial_id, presence: true
end
