package ra.edu.ss09.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.edu.ss09.model.dto.response.ApiResponse;
import ra.edu.ss09.model.entity.Movie;
import ra.edu.ss09.service.MovieService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Movie>>> getAll() {
        List<Movie> movies = movieService.findAll();
        return ResponseEntity.ok(new ApiResponse<>(movies, true, "Lấy danh sách phim thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Movie>> create(@RequestBody Movie movie) {
        try {
            Movie saved = movieService.createMovie(movie);
            logger.info("Thêm phim: {} vào lúc {}", saved.getTitle(), LocalDateTime.now());
            return ResponseEntity.ok(new ApiResponse<>(saved, true, "Thêm phim thành công"));
        } catch (Exception e) {
            logger.error("Lỗi khi thêm phim: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>(null, false, "Lỗi khi thêm phim"));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Movie>> update(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            Movie old = movieService.findById(id);
            Movie updated = movieService.update(id, movie);
            logger.warn("\u001B[33mThông tin cũ: {}\u001B[0m", old); // vàng
            logger.info("\u001B[32mThông tin mới: {}\u001B[0m", updated); // xanh
            return ResponseEntity.ok(new ApiResponse<>(updated, true, "Cập nhật phim thành công"));
        } catch (Exception e) {
            logger.error("\u001B[31mLỗi khi cập nhật phim: {}\u001B[0m", e.getMessage(), e); // đỏ
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>(null, false, "Lỗi khi cập nhật phim"));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        try {
            Movie deleted = movieService.findById(id);
            movieService.delete(id);
            logger.error("\u001B[31mXóa thành công\u001B[0m \u001B[32m{}\u001B[0m", deleted); // đỏ + xanh
            return ResponseEntity.ok(new ApiResponse<>(null, true, "Xóa phim thành công"));
        } catch (Exception e) {
            logger.error("Lỗi khi xóa phim: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(new ApiResponse<>(null, false, "Lỗi khi xóa phim"));
        }
    }
}
