$(document).ready(
		function() {
			
			$('#startDateInput').datepicker();
            $('#endDateInput').datepicker();
            
            $('#startDateInput').change(function(){
            	$('#startTime').val($('#startDateInput').val());
            	$('#search-box-form').submit();
            });
            
            $('#endDateInput').change(function(){
            	$('#endTime').val($('#endDateInput').val());
            	$('#search-box-form').submit();
            });
           
			$('#area_ul span').each(function(index, item) {
				if ($(item).attr('data-id') == $('#area').val()) {
					$(item).addClass('srh_all_on');
				}
			});
			$('#area_ul span').click(function(e) {
				$('#area_ul span').removeClass('srh_all_on');
				$(e.target).addClass('srh_all_on');
				$('#area').val($(e.target).attr('data-id'));
				
				$('#search-box-form').submit();
			});

			$('#priceRange_ul span').each(function(index, item) {
				if ($(item).attr('data-id') == $('#priceRange').val()) {
					$(item).addClass('srh_all_on');
				}
			});
			$('#priceRange_ul span').click(function(e) {
				$('#priceRange_ul span').removeClass('srh_all_on');
				$(e.target).addClass('srh_all_on');
				$('#priceRange').val($(e.target).attr('data-id'));
				$('#search-box-form').submit();
			});
			$('#layout_ul span').each(function(index, item) {
				if ($(item).attr('data-id') == $('#layout').val()) {
					$(item).addClass('srh_all_on');
				}
			});
			$('#layout_ul span').click(function(e) {
				$('#layout_ul span').removeClass('srh_all_on');
				$(e.target).addClass('srh_all_on');
				$('#layout').val($(e.target).attr('data-id'));
				$('#search-box-form').submit();
			});

			$('#features_ul span').each(function(index, item) {
				$('.features').each(function(index, f) {
					if ($(item).attr('data-id') == $(f).val()) {
						$(item).addClass('srh_all_on');
					}
				});
			});

			var rebuildFeaturesData = function() {
				$('#search-box-form #features').remove();

				$('#features_ul span').each(
						function(index, item) {
							if ($(item).hasClass('srh_all_on')) {
								var f = $('<input/>').addClass('features').attr('name', 'features')
										.attr('id', 'features').attr('type',
												'hidden').attr('value',
												$(item).attr('data-id')).attr(
												'autocomplete', 'off');
								$('#search-box-form').append(f);
							}
						});
			};

			$('#features_ul span').click(function(e) {
				if ($(e.target).attr('data-id') == 0) {
					$('#features_ul span').removeClass('srh_all_on');
					$(e.target).addClass('srh_all_on');
				} else {
					$('#features_ul span').each(function(index, item) {
						if ($(item).attr('data-id') == 0) {
							$(item).removeClass('srh_all_on');
						}
					});
					if($(e.target).hasClass('srh_all_on')){
						$(e.target).removeClass('srh_all_on');
					}else{
						$(e.target).addClass('srh_all_on');
					}
					var onCount = 0;
					$('#features_ul span').each(function(index,item){
						if($(item).hasClass('srh_all_on')){
							onCount++;
						}
					});
					if(onCount == 0){
						$('#features_ul .srh_all').addClass('srh_all_on');
					}
					
				}
				rebuildFeaturesData();
				$('#search-box-form').submit();
			});
			
			$('#enterTime_ul span').each(function(index, item) {
				if ($(item).attr('data-id') == $('#enterTime').val()) {
					$(item).addClass('srh_all_on');
				}
			});
			$('#enterTime_ul span').click(function(e) {
				$('#enterTime_ul span').removeClass('srh_all_on');
				$(e.target).addClass('srh_all_on');
				$('#enterTime').val($(e.target).attr('data-id'));
				$('#search-box-form').submit();
			});
			
			$('#leaseType_ul span').each(function(index, item) {
				if ($(item).attr('data-id') == $('#leaseType').val()) {
					$(item).addClass('srh_all_on');
				}
			});
			$('#leaseType_ul span').click(function(e) {
				$('#leaseType_ul span').removeClass('srh_all_on');
				$(e.target).addClass('srh_all_on');
				$('#leaseType').val($(e.target).attr('data-id'));
				$('#search-box-form').submit();
			});
			
			$('#sort_ul a').click(function(e){
				$('#sortType').val($(e.target).attr('data-id'));
				$('#search-box-form').submit();
			});
				
			$('.f_map').click(function(){
				$('#color-box').colorbox({
					html:"<h1>Welcome</h1>"
				});
			});
			
		});