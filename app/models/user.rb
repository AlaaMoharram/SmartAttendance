class User < ApplicationRecord
	has_many :attendance
	has_many :UserTutorial
	has_many :tutorials, through: UserTutorial

	validates_presence_of :name, :username, :password, :role
end
