var app = angular.module('myapp',[]);
	app.controller('showController',function($scope,$timeout,$http){

		var strUrl = document.location.href;
		var init_check_isright = $http({
			method: 'GET',
			url: strUrl+'rest/showcase/getSoftwareInfo'
		});
		init_check_isright.then(function(resp){
			$scope.line = {
				'one':resp.data[0].value1,
				'second':resp.data[0].value2,
				'third':resp.data[0].value3
			}
		},function(resp){
			alert('Sorry, Network Error;Please try again');
		})

		$scope.status = {
			'software':true,
			'school':false,
			'enterprises':false,
			'epiboly':false
		}
		$scope.resize = function(){
			var  _width = Math.floor( ($('#choose_button_frame').width() - 960)/3) - 1;
			var _pic_width = Math.floor($('#content_frame').width() / 2.5);
			var _pic_hight = Math.floor($(window).height() - 381);
			var _pic_hight_one = Math.floor($('#content_right_rim').height());
			$('#content_left_rim').width(_pic_width);
			if(_pic_hight < _pic_hight_one){
				$('#content_left_rim').height(_pic_hight_one);
			}else{
				$('#content_left_rim').height(_pic_hight);
			}			
			$('.button_margin_left').css('margin-right',_width);
		}
		$scope.resize();
		$(window).resize(function(){
			$scope.resize();
			$scope.changeLine();
		});

		$scope.removeLineClass = function(){
			$('#line_one').removeClass('one_line');
			$('#line_second').removeClass('one_line');
			$('#line_third').removeClass('one_line');
		}

		$scope.changeLineHeight = function(id){
			var _height = $('#'+id).outerHeight();
			if(_height < 80){
				$('#'+id).addClass('one_line');
			}
		}

		$scope.changeLine = function(){
			$timeout(function(){
				$scope.removeLineClass();
				$scope.changeLineHeight('line_one');
				$scope.changeLineHeight('line_second');
				$scope.changeLineHeight('line_third');
			},50);
		}
		
		$scope.changePage = function(name){
			if($scope.status[name] === true){
				return
			}
			$('#content_frame').fadeOut();
			$scope.status = {
				'software':false,
				'school':false,
				'enterprises':false,
				'epiboly':false
			}
			$scope.status[name] = true;
			$timeout(function(){
				if(name === 'software'){
					$('#content_left_rim').removeClass();
					$('#content_left_rim').addClass('software');

					var check_isright = $http({
						method: 'GET',
						url: strUrl+'rest/showcase/getSoftwareInfo'
					});
					check_isright.then(function(resp){
						$scope.line = {
							'one':resp.data[0].value1,
							'second':resp.data[0].value2,
							'third':resp.data[0].value3
						}
						$scope.changeLine();
					},function(resp){
						alert('Sorry Network Error;Please try again - getSoftwareInfo');
					})

				}else if(name === 'school'){
					$('#content_left_rim').removeClass();
					$('#content_left_rim').addClass('highschool');
					var check_isright = $http({
						method: 'GET',
						url: strUrl+'rest/showcase/getSchoolInfo'
					});
					check_isright.then(function(resp){
						$scope.line = {
							'one':resp.data[0].value1,
							'second':resp.data[0].value2,
							'third':resp.data[0].value3
						}
						$scope.changeLine();
					},function(resp){
						alert('Sorry Network Error;Please try again - getSchoolInfo');
					})
				}else if(name === 'enterprises'){
					$('#content_left_rim').removeClass();
					$('#content_left_rim').addClass('enterprises');
					var check_isright = $http({
						method: 'GET',
						url: strUrl+'rest/showcase/getEnterprisesInfo'
					});
					check_isright.then(function(resp){
						$scope.line = {
							'one':resp.data[0].value1,
							'second':resp.data[0].value2,
							'third':resp.data[0].value3
						}
						$scope.changeLine();
					},function(resp){
						alert('Sorry Network Error;Please try again - getEnterprisesInfo');
					})
				}else if(name === 'epiboly'){
					$('#content_left_rim').removeClass();
					$('#content_left_rim').addClass('epiboly');
					var check_isright = $http({
						method: 'GET',
						url: strUrl+'rest/showcase/getEpibolyInfo'
					});
					check_isright.then(function(resp){
						$scope.line = {
							'one':resp.data[0].value1,
							'second':resp.data[0].value2,
							'third':resp.data[0].value3
						}
						$scope.changeLine();
					},function(resp){
						alert('Sorry Network Error;Please try again - getEpibolyInfo');
					})
				}else{
					alert('Sorry Network Error;Please Reload Current Page');
				}
				$('#content_frame').fadeIn();
			},500)
			

		}
	})