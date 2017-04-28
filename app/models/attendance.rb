class Attendance < ApplicationRecord
  belongs_to :user
  belongs_to :tutorial

  validates_presence_of :user_id, :tutorial_id, :tut_date, :tut_start_time
end
