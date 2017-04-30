class Room < ApplicationRecord
	has_many :tutorial
	has_many :beacon

	# accepted format example: C7.202
	VALID_NAME_REGEX = /\A[a-zA-Z][1-9]+\.[0-9]+\z/i

	validates :name, presence: true, format: { with: VALID_NAME_REGEX }
	validates_uniqueness_of :name, :case_sensitive => false

end

