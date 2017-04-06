class CreateBeacons < ActiveRecord::Migration[5.0]
  def change
    create_table :beacons do |t|
      t.integer :uuid
      t.references :room, foreign_key: true

      t.timestamps
    end
  end
end
