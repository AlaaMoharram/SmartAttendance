class ChangeTutTimeType < ActiveRecord::Migration[5.0]
  def change
  	change_column(:attendances, :tut_time, :string)
  end
end
