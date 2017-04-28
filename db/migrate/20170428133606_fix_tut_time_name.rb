class FixTutTimeName < ActiveRecord::Migration[5.0]
  def change
  	rename_column :attendances, :tut_time, :tut_start_time
  end
end
