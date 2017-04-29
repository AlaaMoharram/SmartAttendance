class AddNameToBeacons < ActiveRecord::Migration[5.0]
  def change
    add_column :beacons, :beacon_name, :string
  end
end
