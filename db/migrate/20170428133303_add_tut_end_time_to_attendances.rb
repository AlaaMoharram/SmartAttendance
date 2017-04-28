class AddTutEndTimeToAttendances < ActiveRecord::Migration[5.0]
  def change
    add_column :attendances, :tut_end_time, :string
  end
end
