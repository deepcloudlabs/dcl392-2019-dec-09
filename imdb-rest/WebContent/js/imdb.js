function SearchViewModel(){
	var self= this;
	
	this.fromYear= ko.observable(1970);
	this.toYear= ko.observable(1979);
	this.genre=  ko.observable("Drama");
	this.genres= ko.observableArray([]);
	this.movies= ko.observableArray([]);
	
	this.init= function(){
		$.ajax({
	        method : "GET",
	        url : "http://localhost:8080/imdb-rest-0.0.1-SNAPSHOT/api/v1/genres",
	        success : function(genres) {
	            self.genres(genres.map( genre => genre.name));
	        }
	    });		
	}

	this.search= function(){
        $.ajax({
            method:"GET",
            url: "http://localhost:8080/imdb-rest-0.0.1-SNAPSHOT/api/v1/movies?from="
            	+self.fromYear()+"&to="
            	+self.toYear()+"&genre="
            	+self.genre(),
            success: function(movies){
                self.movies(movies);
            }
        });	}
}