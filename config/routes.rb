Rails.application.routes.draw do

  # get '/login' => 'sessions#new'
  # post '/login' => 'sessions#create'
  # get '/logout' => 'sessions#destroy'
  
  # users controller
  put '/users/:user_id' => 'users#update'
  post '/users' => 'users#create'
  get '/users/:username/tutorial' => 'users#getActiveTutorial'
  get '/users/:username/tutorials' => 'users#getAllTutorials'

  # tutorials controller
  post '/tutorials' => 'tutorials#create'
  put '/tutorials/:tutorial_id' => 'tutorials#update'
  get '/tutorials/:name/room' => 'tutorials#findRoom'
  get '/tutorials/:name/students' => 'tutorials#getAllStudents'

  # rooms controller
  post '/rooms' => 'rooms#create'
  get 'rooms/:name/beacons' => 'rooms#getBeacons'

  # beacons controller
  post '/beacons' => 'beacons#create'

  # attendances controller
  post '/attendances' => 'attendances#create'
  put '/attendances/:id' => 'attendances#update'
  get '/attendances/:user_id' => 'attendances#getAllForStudent'
  get '/attendances/:tutorial_id' => 'attendances#getAllForTutorial'

end
