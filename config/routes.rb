Rails.application.routes.draw do
  
  # users controller
  put '/users/:id' => 'users#update'
  post '/users' => 'users#create'
  get '/users/:id/tutorials/:id' => 'users#getActiveTutorial'
  get '/users.:id/tutorials' => 'users#getAllTutorials'

  # tutorials controller
  post '/tutorials' => 'tutorials#create'
  put '/tutorials/:id' => 'tutorials#update'
  get '/tutorials/:id/rooms/:id' => 'tutorials#findRoom'
  get '/tutorials/:id/users' => 'tutorials#getAllStudents'

  # rooms controller
  post '/rooms' => 'rooms#create'
  get '/rooms/:id' => 'rooms#getRoomID'
  get 'rooms/:id/beacons' => 'rooms#getBeacons'

  # beacons controller
  post '/beacons' => 'beacons#create'

  # attendances controller
  post '/attendances' => 'attendances#create'
  put '/attendances/:id' => 'attendances#update'
  get '/attendances/:user_id' => 'attendances#getAllForStudent'
  get '/attendances/:tutorial_id' => 'attendances#getAllForTutorial'

end
