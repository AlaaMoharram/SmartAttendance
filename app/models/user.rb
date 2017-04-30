class User < ApplicationRecord
	has_many :attendance
	has_many :UserTutorial
	has_many :tutorials, through: UserTutorial

	validates_presence_of :name, :username, :password, :role

	# accepted format: firstname.lastname
	VALID_USERNAME_REGEX = /\A[a-zA-Z]+\.[a-zA-Z]+\z/i

	validates :username, uniqueness: true, uniqueness: { case_sensitive: false }, format: { with: VALID_USERNAME_REGEX }
end
