class CreateAttendances < ActiveRecord::Migration[5.0]
  def change
    create_table :attendances do |t|
      t.references :user, foreign_key: true
      t.references :tutorial, foreign_key: true
      t.boolean :attended, :default => false
      t.date :tut_date
      t.time :tut_time

      t.timestamps
    end
  end
end
