class CreateTutorials < ActiveRecord::Migration[5.0]
  def change
    create_table :tutorials do |t|
      t.string :name
      t.boolean :isActive, :default => false
      t.references :room, foreign_key: true

      t.timestamps
    end
  end
end
