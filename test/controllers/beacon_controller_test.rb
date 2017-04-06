require 'test_helper'

class BeaconControllerTest < ActionDispatch::IntegrationTest
  test "should get create" do
    get beacon_create_url
    assert_response :success
  end

end
