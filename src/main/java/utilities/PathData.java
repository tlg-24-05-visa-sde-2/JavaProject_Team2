package utilities;

import tile.Coordinate;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class PathData {
    @JsonbProperty("trackPath")
    public List<Coordinate> trackPath;

    @JsonbProperty("teamPaths")
    public List<TeamPath> teamPaths;
}