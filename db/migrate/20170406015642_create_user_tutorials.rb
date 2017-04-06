class CreateUserTutorials < ActiveRecord::Migration[5.0]
  def change
    create_table :user_tutorials do |t|
      t.references :user, foreign_key: true
      t.references :tutorial, foreign_key: true

      t.timestamps
    end
  end
end
