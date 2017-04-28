# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20170428133606) do

  create_table "attendances", force: :cascade do |t|
    t.integer  "user_id"
    t.integer  "tutorial_id"
    t.boolean  "attended",       default: false
    t.string   "tut_date"
    t.string   "tut_start_time"
    t.datetime "created_at",                     null: false
    t.datetime "updated_at",                     null: false
    t.string   "tut_end_time"
    t.index ["tutorial_id"], name: "index_attendances_on_tutorial_id"
    t.index ["user_id"], name: "index_attendances_on_user_id"
  end

  create_table "beacons", force: :cascade do |t|
    t.integer  "uuid"
    t.integer  "room_id"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
    t.index ["room_id"], name: "index_beacons_on_room_id"
  end

  create_table "rooms", force: :cascade do |t|
    t.string   "name"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

  create_table "tutorials", force: :cascade do |t|
    t.string   "name"
    t.boolean  "isActive",   default: false
    t.integer  "room_id"
    t.datetime "created_at",                 null: false
    t.datetime "updated_at",                 null: false
    t.index ["room_id"], name: "index_tutorials_on_room_id"
  end

  create_table "user_tutorials", force: :cascade do |t|
    t.integer  "user_id"
    t.integer  "tutorial_id"
    t.datetime "created_at",  null: false
    t.datetime "updated_at",  null: false
    t.index ["tutorial_id"], name: "index_user_tutorials_on_tutorial_id"
    t.index ["user_id"], name: "index_user_tutorials_on_user_id"
  end

  create_table "users", force: :cascade do |t|
    t.string   "name"
    t.string   "username"
    t.string   "password"
    t.integer  "role"
    t.datetime "created_at", null: false
    t.datetime "updated_at", null: false
  end

end
