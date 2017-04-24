class ChangeTutDateType < ActiveRecord::Migration[5.0]
  def change
  	change_column(:attendances, :tut_date, :string)
  end
end
